package com.shuaigef;

import com.shuaigef.cli.CommandExecutor;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/21 13:49
 */
public class Main {
    public static void main(String[] args) {
        // args = new String[] {"generate", "-l", "-o", "-a"};
        // args = new String[] {"config"};
        // args = new String[] {"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}