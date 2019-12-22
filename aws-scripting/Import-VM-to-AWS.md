# Import VM into AWS
This guide describes how to create VM on premises and import such VM image into AWS.
Checkout [this](https://docs.aws.amazon.com/vm-import/latest/userguide/vm-import-ug.pdf) official guide.

* Create VM in VirtualBox or other hyper-visor
* Export VM as OVS image ``test-vm-aws.ova``
* Upload OVS image of VM to AWS s3 bucket ``vmimagedata-import``
* Make sure you have IAM role ``vmimport``, use [this](Create-vmiport-role-AWS.md) guide to create one.
* Create ``containers.json`` file
  ```
  [
    {
      "Description": "My Server OVA",
      "Format": "ova",
      "UserBucket": {
          "S3Bucket": "vmimagedata-import",
          "S3Key": "test-vm-aws.ova"
      }
    }
  ]
  ```
* Import image, this will start import task in background 
  ```
  aws ec2 import-image --description "My server VM" --disk-containers "file://./containers.json"
  ```
* Check state of import task
  ```
  aws ec2 describe-import-image-tasks --import-task-ids
  ```
* Once import task is done, new AMI image should be visible in AWS / EC2.
* Create new instance from imported AMI.   
