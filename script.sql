create schema if not exists cardealership collate utf8_general_ci;

create table if not exists automobiles
(
	id int auto_increment,
	mark varchar(45) not null,
	model varchar(45) not null,
	category varchar(1) not null,
	modification varchar(45) null,
	body varchar(45) not null,
	length int not null,
	width int not null,
	height int not null,
	wheelbase int not null,
	weight int not null,
	maximum_weight int not null,
	engine float not null,
	cylinders int not null,
	power int not null,
	acceleration float not null,
	fuel_consumption_city float not null,
	fuel_consumption_route float not null,
	transmission varchar(45) not null,
	ABS varchar(1) null,
	cruise_control varchar(1) null,
	interior varchar(45) not null,
	climate_control varchar(1) null,
	airbags_front varchar(1) null,
	airbags_side varchar(1) null,
	color varchar(45) not null,
	date_of_manufacture date not null,
	vin_engine varchar(17) not null,
	vin_chassis varchar(17) not null,
	vin_body varchar(17) not null,
	status varchar(1) not null,
	constraint id_UNIQUE
		unique (id),
	constraint vin_body_UNIQUE
		unique (vin_body),
	constraint vin_chassis_UNIQUE
		unique (vin_chassis),
	constraint vin_engine_UNIQUE
		unique (vin_engine)
)
engine=InnoDB;

alter table automobiles
	add primary key (id);

create table if not exists operations
(
	id int auto_increment,
	kind varchar(45) not null,
	id_automobile int not null,
	approval_date date not null,
	constraint id_UNIQUE
		unique (id),
	constraint id_automobile_UNIQUE
		unique (id_automobile)
)
engine=InnoDB;

alter table operations
	add primary key (id);

