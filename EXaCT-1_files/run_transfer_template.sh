#! /bin/bash -l
#$ -cwd
#$ -j y
#$ -m ae
#$ -M ans2077@med.cornell.edu,als2076@med.cornell.edu,kwe2001@med.cornell.edu,joh2028@med.cornell.edu,evs2009@med.cornell.edu
#$ -l h_rt=24:00:00
#$ -pe smp 1
#$ -l h_vmem=5g
rsync -avr svcmyed@ipmingestion-d.weill.cornell.edu:/data/runs/<sampleID> $TMPDIR
rsync -a /home/svcmyed/Transfer.<sampleID>.<timestamp>.run_transfer.sh.manifest $TMPDIR
tar cvf $TMPDIR/<sampleID>.tar -C $TMPDIR <sampleID>
docker run -i --rm  --cpu-period=100000 --cpu-quota=100000  --name "<sampleID>_<timestamp>"  -u 198598:538  -v $TMPDIR:/input -v /gx-dev/analytical-storage/Myeloid_transfer_results:/output -e FILE=/input/Transfer.<sampleID>.<timestamp>.run_transfer.sh.manifest ipm-dc-dtr.weill.cornell.edu/ipm/myeloid-transfer@sha256:a1bbb773dafbedc100a60d048e4d64f26da4fd5f9bd7b46f9a430af338cf04b0
tar xvf /gx-dev/analytical-storage/Myeloid_transfer_results/<sampleID>.tar -C /gx-dev/analytical-storage/Myeloid_transfer_results
rm /gx-dev/analytical-storage/Myeloid_transfer_results/<sampleID>.tar
mv $TMPDIR/*message* $TMPDIR/<sampleID>_<timestamp>.manifest.message
rsync -av /home/svcmyed/Transfer.<sampleID>.<timestamp>.run_transfer.sh /gx-dev/analytical-storage/Myeloid_transfer_results/<sampleID>
rsync $TMPDIR/*message* /gx-dev/analytical-storage/Myeloid_transfer_results/<sampleID>
