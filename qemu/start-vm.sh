#!/bin/bash

# to run after VM is fully installed
sudo qemu-system-aarch64 -nographic -machine virt,gic-version=max -m 20480M -cpu max -smp 8 \
-netdev user,id=vnet,hostfwd=tcp::2222-:22 -device virtio-net-pci,netdev=vnet \
-drive file=disk-image.img,if=none,id=drive0,cache=writeback -device virtio-blk,drive=drive0,bootindex=0 \
-drive file=flash0.img,format=raw,if=pflash -drive file=flash1.img,format=raw,if=pflash