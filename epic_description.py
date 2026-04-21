#!/usr/bin/env python3
"""
Скрипт для обновления описания эпика.
Запускается при PR из feature в epic.
"""

import argparse
import requests
import sys


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--repo', required=True)
    parser.add_argument('--token', required=True)
    parser.add_argument('--pr-id', type=int, required=True)
    args = parser.parse_args()

    print(f"📝 Обновление описания эпика для PR #{args.pr_id}")
    print(f"📦 Репозиторий: {args.repo}")
    
    # Получаем информацию о текущем PR
    url = f"https://api.github.com/repos/{args.repo}/pulls/{args.pr_id}"
    headers = {"Authorization": f"Bearer {args.token}"}
    
    try:
        response = requests.get(url, headers=headers)
        response.raise_for_status()
        pr_data = response.json()
        
        epic_branch = pr_data.get('base', {}).get('ref', 'unknown')
        feature_branch = pr_data.get('head', {}).get('ref', 'unknown')
        
        print(f"🎯 Эпик-ветка: {epic_branch}")
        print(f"🔧 Feature-ветка: {feature_branch}")
        
        # Проверяем, что это действительно PR из feature в epic
        if not feature_branch.startswith('feature/'):
            print("⚠️ Ветка не является feature-веткой. Пропускаем.")
            sys.exit(0)
        
        if not epic_branch.startswith('epic/'):
            print("⚠️ Целевая ветка не является эпиком. Пропускаем.")
            sys.exit(0)
        
        # Ищем PR самой эпик-ветки
        pulls_url = f"https://api.github.com/repos/{args.repo}/pulls"
        params = {"head": epic_branch, "state": "open"}
        epic_response = requests.get(pulls_url, headers=headers, params=params)
        epic_response.raise_for_status()
        epic_prs = epic_response.json()
        
        if epic_prs:
            epic_pr = epic_prs[0]
            print(f"📌 Найден PR эпика: #{epic_pr['number']}")
            
            # Формируем новое описание
            new_body = f"# Эпик: {epic_branch}\n\n"
            new_body += "## Связанные feature-ветки:\n\n"
            new_body += f"- 🔄 **{feature_branch}** (PR #{args.pr_id})\n"
            
            # Обновляем описание
            update_url = f"https://api.github.com/repos/{args.repo}/pulls/{epic_pr['number']}"
            update_data = {"body": new_body}
            update_response = requests.patch(update_url, headers=headers, json=update_data)
            update_response.raise_for_status()
            
            print("✅ Описание эпика обновлено!")
        else:
            print("⚠️ PR для эпик-ветки не найден. Создайте PR из эпика в develop/main.")
            
    except requests.exceptions.RequestException as e:
        print(f"❌ Ошибка при работе с GitHub API: {e}")
        sys.exit(1)
    except Exception as e:
        print(f"❌ Неожиданная ошибка: {e}")
        sys.exit(1)


if __name__ == '__main__':
    main()
