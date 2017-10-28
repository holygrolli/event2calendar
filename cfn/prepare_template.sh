#!/bin/bash

set -o errexit

echo testing output
aws sts get-caller-identity --query Account --output text | xargs echo -n
region="$(aws configure get region)"
account_id="$(aws sts get-caller-identity --query Account --output text | xargs echo -n)"
echo using account ${account_id} in ${region}
sed -e "s/REGION/${AWS_REGION}/g" -e "s/ACCOUNT_ID/${account_id}/g" api-template.yaml > api.yaml