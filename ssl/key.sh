openssl req -new -newkey rsa:4096 -days 3650 -nodes -x509 -subj \
    "/C=BR/ST=PI/L=Teresina/O=Unidata/CN=pintos.com.br" -keyout \
    ./ssl.key -out ./ssl.crt
