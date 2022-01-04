package ink.heshang.impl;

import ink.heshang.CourseInfo;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/1/3 21:40
 * version: 1.0
 */
public class EnglishCourseInfo implements CourseInfo {
    /**
     * 实现类，必须要有无参构造方法，但是类会默认自带
     */
    public EnglishCourseInfo() {
    }

    @Override
    public void info() {
        System.out.println("English course");
    }
}
