# PGP Crypto Service

## Description
This is PGP encryption and decryption example, a security program used to decrypt and encrypt email,  
authenticate email messages through digital signatures and file encryption.

## How to Run
1. Clone the repository.
2. Run `EncryptionTest` for unit tests.
3. Run `DecryptionTest` for unit tests.

#### Steps to Generate the Key Pairs:
1. **Generate a Private Key:**
    - Use the following command to generate the keys:
      ```
      gpg --full-generate-key
      
      Please select what kind of key you want:
        (1) RSA and RSA (default)
        (2) DSA and Elgamal
        (3) DSA (sign only)
        (4) RSA (sign only)
      Your selection? 1
      
      RSA keys may be between 1024 and 4096 bits long.
      What keysize do you want? (2048) 4096
      
      Please specify how long the key should be valid.
        0 = key does not expire
        <n>  = key expires in n days
        <n>w = key expires in n weeks
        <n>m = key expires in n months
        <n>y = key expires in n years
      Key is valid for? (0) 1y
      
      Key expires at Mon 01 Jun 2023 10:42:12 AM PDT
      Is this correct? (y/N) y
      
      GnuPG needs to construct a user ID to identify your key.
      Real name: John Doe
      Email address: john.doe@example.com
      Comment: My PGP key
      You selected this USER-ID:
      "John Doe (My PGP key) <anyEmailAddress@example.com>"
      Change (N)ame, (E)mail, or (C)omment? (N/E/C) n
      
      You need a Passphrase to protect your secret key.
      Enter passphrase: ************
      Repeat passphrase: ************
      
      gpg --list-keys
      
      gpg --list-secret-keys
      
      gpg --export -a "anyEmailAddress@example.com" > public_key.asc
      
      gpg --export-secret-keys -a "anyEmailAddress@example.com" > private_key.asc
      ```
      This command generates a Public and Private key using the RSA algorithm and encrypts it with PGP encryption.