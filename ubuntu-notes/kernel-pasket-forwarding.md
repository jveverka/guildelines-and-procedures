# Make ubuntu forward packets
By default, packet forwarding is disabled in ubuntu. If you want to use ubuntu server as router, packet forwarding needs to be enabled in kernel.
In ``/etc/sysctl.conf`` uncomment line ``net.ipv4.ip_forward=1`` and set same value in runtime ``echo 1 > /proc/sys/net/ipv4/ip_forward``.
In ``/etc/ufw/sysctl.conf`` uncomment line ``net/ipv4/ip_forward=1``. 

