-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2025 a las 07:09:03
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `adopcion_mascotas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administracion`
--

CREATE TABLE `administracion` (
  `Id_admin` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administracion`
--

INSERT INTO `administracion` (`Id_admin`, `usuario`, `clave`) VALUES
(1, 'Admin', 'ad123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota`
--

CREATE TABLE `mascota` (
  `id_mascota` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `tipo` varchar(15) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `estado` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mascota`
--

INSERT INTO `mascota` (`id_mascota`, `nombre`, `tipo`, `edad`, `descripcion`, `estado`) VALUES
(1, 'Rocky', 'Gato', 2, 'Un gato macho de color gris con café muy jugeton', 'Adoptado'),
(2, 'Maya', 'Perro', 5, 'Una perra mestiza de tamaño mediano, muy amigable y protectora.', 'Adoptado'),
(3, 'Silvestre', 'Gato', 1, 'Gato siamés, muy tranquilo y le gusta dormir mucho.', 'Disponible'),
(4, 'Sheldon', 'Tortuga', 10, 'Tortuga de tierra, tranquila y le gusta tomar el sol.', 'En Proceso'),
(5, 'Perla', 'Hamster', 1, 'Hámster ruso de color blanco, muy activo por las noches.', 'Adoptado'),
(6, 'Milo', 'Gato', 7, 'Veterano tranquilo, ideal para personas que buscan compañía sin mucho ajetreo.', 'Disponible'),
(7, 'Daisy', 'Perro', 2, 'Olfato increíble y personalidad curiosa, necesita estimulación y paseos.', 'En Proceso'),
(8, 'Chloe', 'Gato', 3, 'Grande y gentil, con un carácter muy dulce y sociable.', 'Disponible'),
(9, 'Buddy', 'Perro', 1, 'Pequeño y con mucha personalidad, le encanta la atención.', 'En Proceso'),
(10, 'Sombra', 'Hámster', 2, 'Muy rápido y enérgico, perfecto para observar y con un hábitat grande.', 'Disponible'),
(11, 'Kiko', 'Pez', 0, 'Colores vibrantes, ideal para acuarios pequeños y tranquilos.', 'Disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `ciudad` varchar(35) DEFAULT NULL,
  `clave` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `nombre`, `correo`, `ciudad`, `clave`) VALUES
(4, 'Luis Garcia', 'Garcia2398@gmail.com', 'Plan del pino', NULL),
(5, 'Nelson Mejía', 'MejiaSv2345@gmail.com', 'San Salvador', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `ID` int(11) NOT NULL,
  `id_mascota` int(11) DEFAULT NULL,
  `id_persona` int(11) DEFAULT NULL,
  `fecha_solicitud` date DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administracion`
--
ALTER TABLE `administracion`
  ADD PRIMARY KEY (`Id_admin`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`id_mascota`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `id_mascota` (`id_mascota`),
  ADD KEY `id_persona` (`id_persona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administracion`
--
ALTER TABLE `administracion`
  MODIFY `Id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `id_mascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`id_mascota`) REFERENCES `mascota` (`id_mascota`),
  ADD CONSTRAINT `solicitud_ibfk_2` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
