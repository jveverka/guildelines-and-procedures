# i7 CPU throttling

Install tooling:
```
sudo apt install i7z stress powertop powerstat
cat /sys/devices/system/cpu/intel_pstate/max_perf_pct
```

Monitor CPU parameters:
```
sudo i7z
```

Stress CPU 
```
stress -c 8
```

Set max CPU performance to 50%, 100%
```
echo 50 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
echo 100 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
```

Turn off/on turbo boost:
```
echo 1 > /sys/devices/system/cpu/intel_pstate/no_turbo
echo 0 > /sys/devices/system/cpu/intel_pstate/no_turbo
```

Low perfomance setup example:
```
echo 1 > /sys/devices/system/cpu/intel_pstate/no_turbo
echo 37 > /sys/devices/system/cpu/intel_pstate/min_perf_pct
echo 37 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
```

Max perfomance setup example:
```
echo 0 > /sys/devices/system/cpu/intel_pstate/no_turbo
echo 37 > /sys/devices/system/cpu/intel_pstate/min_perf_pct
echo 100 > /sys/devices/system/cpu/intel_pstate/max_perf_pct
```

