Estructura del proyecto(Documentacion en cada archivo principal)

/Parcial2-Laboratorio
│
├── /src
│   └── /main
│       └── /java
│           └── /org
│               └── /example
│                   ├── /dao
│                   │   └── /impl
│                   │       ├── CursoDAOClass.java
│                   │       ├── EstudianteDAOClass.java
│                   │       └── GenericInterfaceDAO.java
│                   │
│                   ├── /modelo
│                   │   ├── Curso.java
│                   │   └── Estudiante.java
│                   │
│                   ├── /util
│                   │   └── ConexionBD.java
│                   │
│                   └── Main.java
│
├── /resources
│   └── log4j2.xml (archivo de configuración de logging si usas Log4j2)
│
├── .gitignore
├── README.md
├── build.gradle (o pom.xml si usas Maven)
└── LICENSE
