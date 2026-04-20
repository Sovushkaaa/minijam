#!/usr/bin/env python3
"""
Скрипт для обновления описания эпика.
Запускается при PR из feature в epic.
"""

import argparse
import requests
import re


def get_pr_info(repo: str, token: str, pr_id: int) -> dict:
    """Получает информацию о PR."""
    url = f"https://api.github.com/repos/{repo}/pulls/{pr_id}"
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.get(url, headers=headers)
    response.raise_for_status()
    return response.json()


def get_epic_prs(repo: str, token: str, epic_branch: str) -> list:
    """Находит все PR, нацеленные на эпик-ветку."""
    url = f"https://api.github.com/repos/{repo}/pulls"
    headers = {"Authorization": f"Bearer {token}"}
    params = {"base": epic_branch, "state": "all"}
    response = requests.get(url, headers=headers, params=params)
    response.raise_for_status()
    return response.json()


def update_pr_description(repo: str, token: str, pr_id: int, new_body: str):
    """Обновляет описание PR."""
    url = f"https://api.github.com/repos/{repo}/pulls/{pr_id}"
    headers = {"Authorization": f"Bearer {token}"}
    data = {"body": new_body}
    response = requests.patch(url, headers=headers, json=data)
    response.raise_for_status()


def extract_task_info(pr_body: str) -> str:
    """Извлекает информацию о задаче из описания PR."""
    if not pr_body:
        return "Нет описания"
    
    # Ищем строку "Задача:" или "Task:"
    match = re.search(r'(?:Задача|Task):\s*(.+?)(?:\n|$)', pr_body, re.IGNORECASE)
    if match:
        return match.group(1).strip()
    
    # Берём первую строку
    first_line = pr_body.split('\n')[0]
    return first_line[:50] + "..." if len(first_line) > 50 else first_line


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--repo', required=True)
    parser.add_argument('--token', required=True)
    parser.add_argument('--pr-id', type=int, required=True)
    args = parser.parse_args()

    print(f"📝 Обновление описания эпика для PR #{args.pr_id}")

    # Получаем информацию о текущем PR (из feature в epic)
    current_pr = get_pr_info(args.repo, args.token, args.pr_id)
    epic_branch = current_pr['base']['ref']
    feature_branch = current_pr['head']['ref']

    print(f"🎯 Эпик-ветка: {epic_branch}")
    print(f"🔧 Feature-ветка: {feature_branch}")

    # Находим все PR, нацеленные на эту эпик-ветку
    all_epic_prs = get_epic_prs(args.repo, args.token, epic_branch)

    # Формируем описание
    description = f"# Эпик: {epic_branch}\n\n"
    description += "## Связанные feature-ветки:\n\n"

    for pr in all_epic_prs:
        status = "✅" if pr['state'] == 'closed' else "🔄"
        task_info = extract_task_info(pr.get('body', ''))
        description += f"- {status} **{pr['head']['ref']}**\n"
        description += f"  - PR: #{pr['number']}\n"
        description += f"  - Задача: {task_info}\n"
        description += f"  - Статус: {'Закрыт' if pr['state'] == 'closed' else 'Открыт'}\n\n"

    # Находим PR самой эпик-ветки (обычно это PR в develop или main)
    epic_prs = requests.get(
        f"https://api.github.com/repos/{args.repo}/pulls",
        headers={"Authorization": f"Bearer {args.token}"},
        params={"head": epic_branch}
    ).json()

    if epic_prs:
        epic_pr = epic_prs[0]
        print(f"📌 Найден PR эпика: #{epic_pr['number']}")
        update_pr_description(args.repo, args.token, epic_pr['number'], description)
        print("✅ Описание эпика обновлено!")
    else:
        print("⚠️ PR для эпик-ветки не найден. Описание не обновлено.")


if __name__ == '__main__':
    main()
