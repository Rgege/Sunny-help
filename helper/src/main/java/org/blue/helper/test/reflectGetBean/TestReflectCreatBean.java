package org.blue.helper.test.reflectGetBean;

import org.blue.helper.StringHelper.aop.annotation.GetBean;

@GetBean
public class TestReflectCreatBean {
    private String className="TestReflectCreatBean";
    private String classSay="this is TestReflectCreatBean bean";

    @Override
    public String toString() {
        return "TestReflectCreatBean{" +
                "className='" + className + '\'' +
                ", classSay='" + classSay + '\'' +
                '}';
    }
}
