import sys
import github

if __name__ == "__main__":
    params = []
    for param in sys.argv:
        params.append(param)
        
    if len(params) != 4:
        raise ValueError("Not enough parameters")
    if not params[3].isdigit():
        raise ValueError("Progect Id have to be an integer")
      
    print('test-----')
       
    gl = github.Github(params[1], params[2])
    gl.auth()
    
    print('gl.auth()')
    
    current_user = gl.user
    project = gl.projects.get(int(params[3]))
    mrs = project.mergerequests.list(state='opened')
    
    print(mrs)
    
    for mr in mrs:
        print('mr')
        mr_ = mr.asdict()
        if 'epic' in mr_['source_branch']:
            branches = []
            epic_name = mr_['source_branch'].split('/')[1]
            mr.description = ""
            state_task = 0
            name_task = 'NoNameTask'
            ref_mr = '!None'
            feature_name = 'NoNameFeature'
            for branch in project.branches.list():
                branch = branch.asdict()
                name = branch['name'].split('/')
                feature_name = branch['name']
                state_task = 0
                name_task = 'NoNameTask'
                ref_mr = '!None'
                if 'feature' == name[0] and epic_name == name[1]:
                    mr_branch_id = project.mergerequests.list(source_branch=branch['name'], target_branch=mr_['source_branch'])
                    task = -1
                    if mr_branch_id:
                        mr_branch = mr_branch_id[0].asdict()
                        ref_mr = mr_branch['references']['short']
                        if '#' in mr_branch['description']:
                            is_num = mr_branch['description'].split('#')[1].split()[0]
                            if is_num.isdigit():
                                task = int(is_num)
                        if task > -1:
                            issue = project.issues.get(task)
                            state_task = 1 if issue.asdict()['state'] == 'closed' else 0
                            name_task = issue.asdict()['title']
                    print(mr_branch_id)
                    mr.description += f'| {ref_mr} | {name_task} | {feature_name} | {"[✓]" if state_task else "[ ]"} |\n\n'
            mr.save()

