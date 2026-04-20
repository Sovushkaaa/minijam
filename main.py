#!/usr/bin/env python3
"""
Скрипт для проверки размера Pull Request'а.
Запуск: python main.py --repo OWNER/REPO --token GITHUB_TOKEN --pr-id PR_NUMBER
"""

import argparse
import sys
import requests

# Лимиты по типу ветки (в строках кода)
LIMITS = {
    'feature': 300,
    'bugfix': 150,
    'refactor': 400,
}


def get_pr_info(repo: str, token: str, pr_id: int) -> dict:
    """Получает информацию о PR из GitHub API."""
    url = f"https://api.github.com/repos/{repo}/pulls/{pr_id}"
    headers = {
        "Authorization": f"Bearer {token}",
        "Accept": "application/vnd.github.v3+json"
    }
    response = requests.get(url, headers=headers)
    response.raise_for_status()
    return response.json()


def get_pr_files(repo: str, token: str, pr_id: int) -> list:
    """Получает список изменённых файлов в PR."""
    url = f"https://api.github.com/repos/{repo}/pulls/{pr_id}/files"
    headers = {
        "Authorization": f"Bearer {token}",
        "Accept": "application/vnd.github.v3+json"
    }
    response = requests.get(url, headers=headers)
    response.raise_for_status()
    return response.json()


def calculate_changes(files: list) -> int:
    """Вычисляет общий размер изменений (добавлено - удалено)."""
    total_additions = sum(f.get('additions', 0) for f in files)
    total_deletions = sum(f.get('deletions', 0) for f in files)
    return total_additions - total_deletions


def get_branch_type(branch_name: str) -> str:
    """Определяет тип ветки по названию."""
    if branch_name.startswith('feature/'):
        return 'feature'
    elif branch_name.startswith('bugfix/'):
        return 'bugfix'
    elif branch_name.startswith('refactor/'):
        return 'refactor'
    else:
        return 'unknown'


def main():
    parser = argparse.ArgumentParser(description='Проверка размера PR')
    parser.add_argument('--repo', required=True, help='Репозиторий в формате OWNER/REPO')
    parser.add_argument('--token', required=True, help='GitHub токен')
    parser.add_argument('--pr-id', type=int, required=True, help='Номер Pull Request')
    args = parser.parse_args()

    print(f"🔍 Проверка PR #{args.pr_id} в репозитории {args.repo}")

    # Получаем информацию о PR
    pr_info = get_pr_info(args.repo, args.token, args.pr_id)
    branch_name = pr_info['head']['ref']
    branch_type = get_branch_type(branch_name)

    print(f"📂 Ветка: {branch_name}")
    print(f"🏷️ Тип ветки: {branch_type}")

    if branch_type == 'unknown':
        print(f"⚠️ Неизвестный тип ветки '{branch_name}'. Пропускаем проверку.")
        sys.exit(0)

    # Получаем изменения
    files = get_pr_files(args.repo, args.token, args.pr_id)
    changes = calculate_changes(files)

    print(f"📊 Изменений (добавлено - удалено): {changes} строк")

    # Проверяем лимит
    limit = LIMITS[branch_type]
    if changes > limit:
        print(f"❌ ОШИБКА: Превышен лимит в {limit} строк для ветки типа '{branch_type}'!")
        print(f"   Фактически: {changes} строк")
        sys.exit(1)
    else:
        print(f"✅ ОК: Размер изменений ({changes} строк) в пределах лимита ({limit})")
        sys.exit(0)


if __name__ == '__main__':
    main()
