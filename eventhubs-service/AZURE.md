## Install Azure CLI on MacOs
Install with HomeBrew:
~~~
  > brew update && brew install azure-cli
~~~
Ref:

* [Install Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest)

## Generate the file with the credentials
~~~
    > az login
    > az account set -s 11111111-1111-1111-1111-111111111111
    > az ad sp create-for-rbac --sdk-auth > azure-credentials.json
~~~