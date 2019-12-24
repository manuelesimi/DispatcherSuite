# How to generate the Azure credential file

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
        You have logged in. Now let us find all the subscriptions to which you have access...
        [
          {
            "cloudName": "AzureCloud",
            "id": "...id...",
            "isDefault": true,
            "name": "Microsoft Azure Sponsorship",
            "state": "Enabled",
            "tenantId": "...tenant id...",
            "user": {
              "name": "...email...",
              "type": "user"
            }
          }
        ]
    > az account set -s ...id...
    > az ad sp create-for-rbac --sdk-auth > azure-credentials.json
        Creating a role assignment

~~~

## Template of credential file

The `azure-credentials.json` generated with the above instructions should have the following structure:
```json
{
  "clientId": "...",
  "clientSecret": "...",
  "subscriptionId": "...",
  "tenantId": "...",
  "activeDirectoryEndpointUrl": "https://login.microsoftonline.com",
  "resourceManagerEndpointUrl": "https://management.azure.com/",
  "activeDirectoryGraphResourceId": "https://graph.windows.net/",
  "sqlManagementEndpointUrl": "https://management.core.windows.net:8443/",
  "galleryEndpointUrl": "https://gallery.azure.com/",
  "managementEndpointUrl": "https://management.core.windows.net/"
}
```