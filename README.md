# _Wildlife Tracker_

### _Epicodus: Java Week 4, Advanced Java Topics: Independent Project_

#### By _[**Caleb Stevenson**](https://github.com/CGrahamS)_

## Description

## Specs

| BEHAVIOR                               | INPUT                                                                                                                                              | OUTPUT                                                                                                                                          |
|----------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| Program creates animal entry.          | Example 1:<br>Name: Black Tailed Deer<br>Is the species endangered?<br>No.                                                                         | Black Tailed Deer                                                                                                                               |
|                                        | Example 2:<br>Name: Roosevelt Elk<br>Is the species endangered?<br>Yes.<br>                                                                        | Roosevelt Elk<ul>     <li>Health: Fair</li>     <li>Age: Adult</li> </ul>                                                                       |
| Program creates animal sighting entry. | Example 1:Sighting: <br>Animal Species: Black Tailed Deer <br> Location: Zone A <br> Ranger: Dave                                                  | Sighting #nth: <br>Black Tailed Deer<br>Location: Zone A <br> Ranger: Dave                                                                      |
|                                        | Example 2:Sighting:<br>Animal: Roosevelt Elk<br><ul>     <li>Health: Fair</li>    <li>Age: Adult</li> </ul>Location: Zone A<br>Ranger: Dave        | Sighting #nth: <br>Animal: Roosevelt Elk<br><ul>     <li>Health: Fair</li>    <li>Age: Adult</li> </ul>Location: Zone A <br> Ranger: Dave       |
| Program updates animal entries.        | Update:Name: Black Tail Deer<br>Is the species endangered?<br>Yes.                                                                                 | Black Tail Deer                                                                                                                                 |
| Program updates sighting entries.      | Update:Sighting: <br>Animal: Black Tail Deer <br><ul>     <li>Health: Fair</li>    <li>Age: Adult</li> </ul>Location: Zone B <br> Ranger: Brad     | Sighting #nth: <br>Animal: Black Tail Deer<br><ul>     <li>Health: Fair</li>    <li>Age: Adult</li> </ul>Location: Zone B <br> Ranger: Brad     |
| Program deletes animal entries.        | Delete:Panda Bear                                                                                                                                  |                                                                                                                                                 |
| Program deletes sighting entries.      | Delete:Sighting #nth                                                                                                                               |                                                                                                                                                 || 

## Setup/Installation Requirements

* In your first terminal window:
  * Start postgres: `$ postgres`
* In your second terminal window:
  * Start psql: `$ psql`
  * Create database: `# CREATE DATABASE wildlife_tracker;`
* In your third terminal window:
  * Clone this repository to your desktop: `$ cd Desktop; git clone https://github.com/CGrahamS/wildlife-tracker`
  * Navigate to the hair-salon directory: `$ cd wildlife-tracker`
  * Create database schema from wildlife_tracker.sql: `$ psql wildlife_tracker < wildlife_tracker.sql`
* Back in your second terminal window:
  * Confirm the database has been restored correctly:
    * Connect to wildlife_tracker database: `# \c wildlife_tracker;`
    * Print out database tables: `# \dt;`
    <br>
    NOTE: You should see `animals` and `sightings` tables in the `wildlife_tracker` database.
* Back in your third terminal window:
  * Run the server: `$ gradle run`
* In the browser of your choosing, navigate to "localhost:4567" (tested in Chrome).

## Known Bugs

None

## Support and contact details

Caleb Stevenson: _cgrahamstevenson@gmail.com_

## Technologies Used

_Java,
Spark,
Velocity,
SQL_

### License

This webpage is licensed under the GPL license.

Copyright &copy; 2016 **_Caleb Stevenson_**
