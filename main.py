import sys
import gitlab

if __name__ == "__main__":
    params = []
    for param in sys.argv:
        params.append(param)
    if len(params) != 4:
        raise ValueError("Not enough parameters")
    if not params[3].isdigit():
        raise ValueError("Progect Id have to be an integer")
    gl = gitlab.Gitlab(params[1], params[2])
    gl.auth()
    project = gl.projects.get(int(params[3]))
    mr = project.mergerequests.list()[0]
    addd = 0
    delll = 0
    t_ = mr.asdict()
    target = t_['target_branch']
    source = t_['source_branch']
    state = t_['state']
    if len(mr.changes()["changes"]) > 0 and state == 'merged':
        for c_files in mr.changes()["changes"]:
            str_diff = c_files['diff']
            for strr in str_diff.split('\n'):
                if len(strr) > 0:
                    sign = list(strr)[0]
                    addd += 1 if sign == '+' else 0
                    delll += -1 if sign == '-' else 0
        if 'feature' in source:
            if (addd - delll) > 300:
                raise ValueError("Merge Request feature exceeded the size of 300 lines")
        elif 'bugfix' in source:
            if (addd - delll) > 150:
                raise ValueError("Merge Request bugfix exceeded the size of 150 lines")
        elif 'refactor' in source:
            if (addd - delll) > 400:
                raise ValueError("Merge Request refactor exceeded the size of 400 lines")