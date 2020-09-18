
---------------- Initial setup ----------------
1. install and configure aws-cli
    - https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html

2. install kubectl
     - https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html

3. configure aws-cli
     - https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html#cli-configure-quickstart-config

4. configure kubectl
     - aws eks --region us-east-2 update-kubeconfig --name <EKS_cluster_name>

---------------- (: GET LOGS :)  ----------------
5. get pods
     - kubectl get pods -n <namespace>

6. get logs
     - kubectl logs -n <namespace> <pod_name>

7. get and follow logs
     - kubectl logs -f -n <namespace> <pod_name>

8. switch kubectl context
     - kubectl config use-context arn:aws:eks:us-east-2:214034809976:cluster/<EKS_cluster_name>

