package com.shuaigef.cli.pattern;

/**
 * 遥控器
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/30 12:27
 */
public class RemoteControl {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

}
