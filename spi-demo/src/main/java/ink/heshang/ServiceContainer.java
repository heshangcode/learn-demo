package ink.heshang;


import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/1/3 21:43
 * version: 1.0
 */
public class ServiceContainer {

    private static final HashMap<String, CourseInfo> service = Maps.newHashMap();

    static {
        ServiceLoader<CourseInfo> load = ServiceLoader.load(CourseInfo.class);
        for (CourseInfo courseInfo : load) {
            service.put(courseInfo.getClass().getName(), courseInfo);
        }
    }

    public static CourseInfo getCourseInfo(String clazz) {
        return service.get(clazz);
    }

    public static void main(String[] args) {
        CourseInfo courseInfo = ServiceContainer.getCourseInfo("ink.heshang.impl.ChineseCourseInfo");
        courseInfo.info();

        CourseInfo courseInfo2 = ServiceContainer.getCourseInfo("ink.heshang.impl.EnglishCourseInfo");
        courseInfo2.info();
    }
}
