package org.jetbrains.sbtidea.tasks.packaging.artifact

import sbt.Keys.TaskStreams
import sbt._


class ZipDistBuilder(private val dest: File)(implicit stream: TaskStreams) extends ArtifactBuilder[File, File] {
  override def produceArtifact(source: File): sbt.File = {
    val packager = new ZipPackager(dest.toPath)
    timed(s"Packaging ZIP artifact: $dest", {
      packager.mergeIntoOne(Seq(source.toPath))
    })
    dest
  }
}
