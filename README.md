## [management_node] check/set environment defaults
```shell
export db_admin_password=
export db_user_password=
export DO_API_TOKEN=
```

## [management_node] create inventory
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=" inventory.yml
```

### expected inventory structure
```shell
[test]
1.1.1.1
1.1.1.2
1.1.1.3
1.1.1.4

[prod]
2.2.2.1
2.2.2.2
2.2.2.3
2.2.2.4

[db]
1.1.1.1
2.2.2.1

[app]
1.1.1.2
1.1.1.3
2.2.2.2
2.2.2.3

[lb]
1.1.1.4
2.2.2.4
```

## [management_node] clone app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2" clone-app.yml
```

## [db] upgrade db
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=test" upgrade-db.yml
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=prod" upgrade-db.yml
```

## [prod][db] initial dataset
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=prod" apply-initial-dataset.yml
```

## [test][app] upgrade app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=test" upgrade-app.yml
```

## [test][management_node] test app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=test" test-app.yml
```

## [prod][app] upgrade app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=prod" upgrade-app.yml
```

## push new changes here !

## [management_node] clone app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=??" clone-app.yml
```

## [test][db] dump <source=prod> db to <target=test> db
```shell
time ansible-playbook -i /etc/ansible/hosts -e "source_env=prod target_env=test rev= dump_filename=/tmp/standardstack-dump-`date +'%s'`.sql" dump-db.yml
```

## [test][db] upgrade db
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=test" upgrade-db.yml
```

## [test][app] upgrade app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=test" upgrade-app.yml
```

## [test][management_node] test app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=test" test-app.yml
```

## [prod][db] upgrade db
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=prod" upgrade-db.yml
```

## [prod][app] upgrade app
```shell
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=prod" upgrade-app.yml
```

## [management_node] destroy cluster
```shell
time ansible-playbook -i /etc/ansible/hosts destroy-cluster.yml
```
