#!/usr/bin/env bash
sampleID=$1
timestamp=`date +"%Y%m%d%H%M%S"`
sed -e "s/<sampleID>/$sampleID/g" run_transfer_template.sh  | sed -e "s/<timestamp>/$timestamp/g" > Transfer.${sampleID}.${timestamp}.run_transfer.sh
sed -e "s/<sampleID>/$sampleID/g" run_transfer_manifest_template.sh | sed -e "s/<timestamp>/$timestamp/g" > Transfer.${sampleID}.${timestamp}.run_transfer.sh.manifest

cp Transfer.${sampleID}.${timestamp}.run_transfer.sh.manifest /home/svcmyed/
cp Transfer.${sampleID}.${timestamp}.run_transfer.sh /home/svcmyed/
qsub Transfer.${sampleID}.${timestamp}.run_transfer.sh