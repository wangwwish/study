package com.wwish.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class LintPlugin implements Plugin<Project> {

    void apply(Project project) {

        println "hello, world!"


        project.task('test-task') << {
            println "hello, this is test task!"
        }
    }
}

