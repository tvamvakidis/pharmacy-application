# Pharmacy Application

## Προαπαιτούμενα
- [Java 17+] 
- [Maven] 
- [PostgreSQL]
	(προεπιλεγμένα στοιχεία από `application.yml`):
  		- Host: `localhost`
  		- Port: `5432`
  		- Database: `pharmacy_application`
  		- User: `postgres`
  		- Password: `postgres`

## Βάση Δεδομένων
Πριν τρέξετε την εφαρμογή, πρέπει να δημιουργήσετε τη βάση δεδομένων στην PostgreSQL. 

## Ρυθμίσεις
Η εφαρμογή χρησιμοποιεί Spring Profiles για να διαχωρίζει development και production περιβάλλοντα.  
- Το αρχείο `application.yml` περιέχει τις βασικές ρυθμίσεις.  
- Το αρχείο `application-dev.yml` περιέχει τις ρυθμίσεις για το development περιβάλλον. 
