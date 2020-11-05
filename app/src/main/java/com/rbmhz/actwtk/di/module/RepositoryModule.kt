package  com.rbmhz.actwtk.di.module

import com.rbmhz.actwtk.repository.InterfaceRepository
import com.rbmhz.actwtk.repository.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsImgurGalleryRepository(repository: Repository): InterfaceRepository
}