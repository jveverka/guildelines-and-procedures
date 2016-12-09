#!/bin/bash

SESSION=$USER

tmux -2 new-session -d -s $SESSION
tmux new-window -t $SESSION:1 -n 'SplitTerminalExample'

#split screen into 6 parts
tmux select-pane -t 0
tmux split-window -v -p 66
tmux select-pane -t 1
tmux split-window -v -p 50
tmux select-pane -t 0
tmux split-window -h -p 50
tmux select-pane -t 2
tmux split-window -h -p 50
tmux select-pane -t 4
tmux split-window -h -p 50

tmux select-pane -t 0
tmux send-keys "echo I am No0" C-m
tmux select-pane -t 1
tmux send-keys "echo I am No1" C-m
tmux select-pane -t 2
tmux send-keys "echo I am No2" C-m
tmux select-pane -t 3
tmux send-keys "echo I am No3" C-m
tmux select-pane -t 4
tmux send-keys "echo I am No4" C-m
tmux select-pane -t 5
tmux send-keys "echo I am No5" C-m

#split screen into 8 parts
#tmux select-pane -t 0
#tmux split-window -v -p 50
#tmux select-pane -t 0
#tmux split-window -v -p 50
#tmux select-pane -t 2
#tmux split-window -v -p 50
#tmux select-pane -t 0
#tmux split-window -h -p 50
#tmux select-pane -t 2
#tmux split-window -h -p 50
#tmux select-pane -t 4
#tmux split-window -h -p 50
#tmux select-pane -t 6
#tmux split-window -h -p 50

#split screen into 4 horizontal parts
#tmux select-pane -t 0
#tmux split-window -v -p 50
#tmux select-pane -t 0
#tmux split-window -v -p 50
#tmux select-pane -t 2
#tmux split-window -v -p 50

#split screen into quarters (2 horizontal and 2 vertical)
#tmux select-pane -t 0
#tmux split-window -v -p 50
#tmux select-pane -t 0
#tmux split-window -h -p 50
#tmux select-pane -t 2
#tmux split-window -h -p 50

tmux select-window -t $SESSION:1
tmux -2 attach-session -t $SESSION


