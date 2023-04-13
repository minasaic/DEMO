FROM --platform=linux/x86_64 library/mysql:8.0.30-debian

# LC_ALL適用するために設定
RUN apt-get update && apt-get install -y locales \
    && sed -i -e 's/# \(ja_JP.UTF-8\)/\1/' /etc/locale.gen \
    && locale-gen \
    && update-locale LANG=ja_JP.UTF-8

ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP:ja_JP
ENV LC_ALL ja_JP.UTF-8

COPY ./custom.cnf /etc/mysql/conf.d/
