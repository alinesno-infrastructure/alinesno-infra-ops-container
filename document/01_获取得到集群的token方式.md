```yaml
cat << EOF > kuboard-create-token.yaml
---
apiVersion: v1
kind: Namespace
metadata:
  name: kuboard

---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: kuboard-admin
  namespace: kuboard

---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: kuboard-admin-crb
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: kuboard-admin
  namespace: kuboard

---
apiVersion: v1
kind: Secret
type: kubernetes.io/service-account-token
metadata:
  annotations:
    kubernetes.io/service-account.name: kuboard-admin
  name: kuboard-admin-token
  namespace: kuboard
EOF

kubectl apply -f kuboard-create-token.yaml 
echo -e "\033[1;34m将下面这一行红色输出结果填入到 kuboard 界面的 Token 字段：\033[0m"
echo -e "\033[31m$(kubectl -n kuboard get secret $(kubectl -n kuboard get secret kuboard-admin-token | grep kuboard-admin-token | awk '{print $1}') -o go-template='{{.data.token}}' | base64 -d)\033[0m"
```