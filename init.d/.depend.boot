TARGETS = console-setup mountkernfs.sh alsa-utils ufw hostname.sh x11-common dns-clean apparmor pppd-dns plymouth-log udev keyboard-setup mountdevsubfs.sh brltty procps hwclock.sh checkroot.sh networking urandom checkroot-bootclean.sh bootmisc.sh mountall.sh checkfs.sh mountnfs-bootclean.sh mountnfs.sh mountall-bootclean.sh kmod
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
brltty: mountkernfs.sh udev
procps: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
networking: mountkernfs.sh urandom dns-clean procps
urandom: hwclock.sh
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh udev
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkfs.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
mountall-bootclean.sh: mountall.sh
kmod: checkroot.sh
