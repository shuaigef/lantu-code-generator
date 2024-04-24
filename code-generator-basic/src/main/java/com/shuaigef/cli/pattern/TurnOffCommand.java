package com.shuaigef.cli.pattern;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/11/30 12:22
 */
public class TurnOffCommand implements Command{

    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}
