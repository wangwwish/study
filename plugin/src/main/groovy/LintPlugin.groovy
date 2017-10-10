package com.wwish.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class LintPlugin implements Plugin<Project> {

    void apply(Project project) {

        def hasApp=project.plugins.withType(AppPlugin)
        def hasLibrary=project.plugins.withType(LibraryPlugin)

        if(!hasApp && !hasLibrary){
            throw new IllegalStateException("'android' or 'android-library' plugin required.")
        }

        println "hello, world!"


        project.task('test-task') << {
            println "hello, this is test task!"
        }

        project.dependencies{

        }
    }
}

