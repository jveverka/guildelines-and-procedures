#!/bin/bash

BOOT_IMG=ubuntu-20.04.3-live-server-arm64.iso

# run only to install
sudo qemu-system-aarch64 -nographic -machine virt,gic-version=max -m 16384M -cpu max -smp 4 \
-netdev user,id=vnet,hostfwd=:127.0.0.1:0-:22 -device virtio-net-pci,netdev=vnet \
-drive file=disk-image.img,if=none,id=drive0,cache=writeback -device virtio-blk,drive=drive0,bootindex=0 \
-drive file=${BOOT_IMG},if=none,id=drive1,cache=writeback -device virtio-blk,drive=drive1,bootindex=1 \
-drive file=flash0.img,format=raw,if=pflash -drive file=flash1.img,format=raw,if=pflash
