#!/usr/bin/env bash
sampleID=$1
timestamp=`date +"%Y%m%d%H%M%S"`
working_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

sed -e "s/<sampleID>/$sampleID/g" ${working_dir}/run_transfer_template.sh  | sed -e "s/<timestamp>/$timestamp/g" > ${working_dir}/Transfer.${sampleID}.${timestamp}.run_transfer.sh
sed -e "s/<sampleID>/$sampleID/g" ${working_dir}/run_transfer_manifest_template.sh | sed -e "s/<timestamp>/$timestamp/g" > ${working_dir}/Transfer.${sampleID}.${timestamp}.run_transfer.sh.manifest

cp ${working_dir}/Transfer.${sampleID}.${timestamp}.run_transfer.sh.manifest /home/svcmyed/
cp ${working_dir}/Transfer.${sampleID}.${timestamp}.run_transfer.sh /home/svcmyed/
qsub ${working_dir}/Transfer.${sampleID}.${timestamp}.run_transfer.sh