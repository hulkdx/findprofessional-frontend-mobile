package com.hulkdx.findprofessional.app.test.utils

import com.hulkdx.findprofessional.core.config.PlatformSpecific
import com.hulkdx.findprofessional.core.storage.dataStoreFile
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem


fun deleteDataStoreFile() {
    val ps = get<PlatformSpecific>()
    SystemFileSystem.delete(Path(ps.dataStoreFile()), false)
}
