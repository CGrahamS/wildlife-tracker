--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: CGrahamS
--

CREATE TABLE animals (
    id integer NOT NULL,
    name character varying,
    endangered boolean
);


ALTER TABLE animals OWNER TO "CGrahamS";

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: CGrahamS
--

CREATE SEQUENCE animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animals_id_seq OWNER TO "CGrahamS";

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: CGrahamS
--

ALTER SEQUENCE animals_id_seq OWNED BY animals.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: CGrahamS
--

CREATE TABLE sightings (
    id integer NOT NULL,
    sighting_time timestamp without time zone,
    location character varying,
    ranger character varying,
    health character varying,
    age character varying,
    animal_id integer
);


ALTER TABLE sightings OWNER TO "CGrahamS";

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: CGrahamS
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO "CGrahamS";

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: CGrahamS
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: CGrahamS
--

ALTER TABLE ONLY animals ALTER COLUMN id SET DEFAULT nextval('animals_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: CGrahamS
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: CGrahamS
--

COPY animals (id, name, endangered) FROM stdin;
3	Roosevelt Elk	f
4	Gray Wolf	t
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: CGrahamS
--

SELECT pg_catalog.setval('animals_id_seq', 4, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: CGrahamS
--

COPY sightings (id, sighting_time, location, ranger, health, age, animal_id) FROM stdin;
35	2016-10-02 20:58:44.237799	Zone A	Dave	\N	\N	3
36	2016-10-02 20:59:30.638651	Zone A	Dave	Healthy	Adult	4
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: CGrahamS
--

SELECT pg_catalog.setval('sightings_id_seq', 36, true);


--
-- Name: animals_pkey; Type: CONSTRAINT; Schema: public; Owner: CGrahamS
--

ALTER TABLE ONLY animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: CGrahamS
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: CGrahamS
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "CGrahamS";
GRANT ALL ON SCHEMA public TO "CGrahamS";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

