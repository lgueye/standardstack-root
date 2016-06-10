## [management_node] check/set environment defaults
<pre><code>
export db_admin_password=
export db_user_password=
export DO_API_TOKEN=
</code></pre>

## [management_node] create inventory
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=" inventory.yml
</code></pre>

### expected inventory structure
<pre><code>
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
</code></pre>

## [management_node] clone app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2" clone-app.yml
</code></pre>

## [db] upgrade db
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=test" upgrade-db.yml
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=prod" upgrade-db.yml
</code></pre>

## [prod][db] initial dataset
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=prod" apply-initial-dataset.yml
</code></pre>

## [test][app] upgrade app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=test" upgrade-app.yml
</code></pre>

## [test][management_node] test app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=test" test-app.yml
</code></pre>

## [prod][app] upgrade app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=4ba23de234605b204e886b92343e9de6ae04dab2 target_env=prod" upgrade-app.yml
</code></pre>

## push new changes here !

## [management_node] clone app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=??" clone-app.yml
</code></pre>

## [test][db] dump <source=prod> db to <target=test> db
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "source_env=prod target_env=test rev= dump_filename=/tmp/standardstack-dump-`date +'%s'`.sql" dump-db.yml
</code></pre>

## [test][db] upgrade db
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=test" upgrade-db.yml
</code></pre>

## [test][app] upgrade app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=test" upgrade-app.yml
</code></pre>

## [test][management_node] test app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=test" test-app.yml
</code></pre>

## [prod][db] upgrade db
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=prod" upgrade-db.yml
</code></pre>

## [prod][app] upgrade app
<pre><code>
time ansible-playbook -i /etc/ansible/hosts -e "rev=?? target_env=prod" upgrade-app.yml
</code></pre>

## [management_node] destroy cluster
<pre><code>
time ansible-playbook -i /etc/ansible/hosts destroy-cluster.yml
</code></pre>
