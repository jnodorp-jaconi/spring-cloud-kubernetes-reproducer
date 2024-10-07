# Steps to Reproduce

1. Use [k3d](https://k3d.io/) to create a Kubernetes cluster:

    ```shell
    k3d cluster create --config k3d.yaml
    ```

2. Deploy some test secrets to the Kubernetes cluster:

    ```shell
    kubectl create --filename k8s.yaml
    ```

3. Run the application:

   ```shell
   ./gradlew bootRun
   ```

4. Cleanup:

    ```shell
    k3d cluster delete --config k3d.yaml
    ```
