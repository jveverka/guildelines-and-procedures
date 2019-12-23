# Create vmimport role
This simple guide describes how to create ``vmimport`` role for AWS CLI user in order to be able import VM images from S3 buckets.

* Create JSON file ``trust-policy.json`` with content:
  ```
  {
   "Version": "2012-10-17",
   "Statement": [
   {
   "Effect": "Allow",
   "Principal": { "Service": "vmie.amazonaws.com" },
   "Action": "sts:AssumeRole",
   "Condition": {
   "StringEquals":{
   "sts:Externalid": "vmimport"
   }
   }
   }
   ]
  }
  ```
* Create trust policy:
  ```
  aws iam create-role --role-name vmimport --assume-role-policy-document "file://./trust-policy.json"
  ```
* Create JSON file ``role-policy.jsom`` with content:
  ```
  {
   "Version":"2012-10-17",
   "Statement":[
   {
   "Effect":"Allow",
   "Action":[
   "s3:GetBucketLocation",
   "s3:GetObject",
   "s3:ListBucket"
   ],
   "Resource":[
   "arn:aws:s3:::vmimagedata-import",
   "arn:aws:s3:::vmimagedata-import/*"
   ]
   },
   {
   "Effect":"Allow",
   "Action":[
   "s3:GetBucketLocation",
   "s3:GetObject",
   "s3:ListBucket",
   "s3:PutObject",
   "s3:GetBucketAcl"
   ],
   "Resource":[
   "arn:aws:s3:::vmimagedata-export",
   "arn:aws:s3:::vmimagedata-export/*"
   ]
   },
   {
   "Effect":"Allow",
   "Action":[
   "ec2:ModifySnapshotAttribute",
   "ec2:CopySnapshot",
   "ec2:RegisterImage",
   "ec2:Describe*"
   ],
   "Resource":"*"
   }
   ]
  }
  ```
* Create role policy:
  ```
  aws iam put-role-policy --role-name vmimport --policy-name vmimport --policy-document "file://./role-policy.json"
  ```

