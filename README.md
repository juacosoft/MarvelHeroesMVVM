# Marvel Hero MVVM

Simple overview of use/purpose.

## Description

Marvels hero MVVM es un proyecto realizado consumiendo la api de marvel en arquitectura MVVM 

Tegnologias utilizadas

<ul>
  <Li>MVVM</Li>
  <li>RXAndroi </Li>
  <li>DaggerHilt inyeccion de dependencias</Li>
  <li>Retrofit2</Li>
  <li>Kotlin</Li>
  
</ul> 


## documentacion de la Api https://developer.marvel.com/docs#!/public/getComicsCollection_get_6

#Consideraciones
Basado en patron de diseño MVVM y programacion reactiva se realizo aplicacion online, la implementacion o sincronizacion de archivos de manera offline se implementaria 
MoviesREpository esta tendria los observadores necesarios para la sincronizacion con persistencia de datos DAO la clase MovieDataBase (Extiende de RoomDatabase)
Sincroniza los datos traidos desde la api y estos estarian disponibles para ser injectados en los viewmodels correspondientes 
interface MovileDao contendria las sentencias SQLite pertinentes para: insercion de datos, consulta y actualizacion

##Configuracion
constants/ServerUrls.kt
api_key
hash

## Joaquin Martinez Marulanda



## Version History

* 0.1
    * Initial Release

## License

Este proyecto fue realizado para prueba practica de programacion en patron de diseño MVVM, RxJava, RXAndroid,Inyeccion de dependencia con Dagger2

## Acknowledgments


