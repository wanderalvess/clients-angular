FROM node:latest as angular
WORKDIR /app
COPY package.json /app
RUN npm install --silent
COPY . .
RUN npm run build --prod

FROM nginx:alpine
VOLUME /var/cache/nginx
COPY --from=angular app/dist/clients-front /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf


#antigo
#FROM node:lts-alpine as angular
#ENV NODE_ENV=production
#WORKDIR /app
#COPY package.json /app
#RUN npm install --silent
#COPY . .
##RUN npm run build --prod
#FROM nginx:alpine
#VOLUME /var/cache/nginx
#COPY --from=angular app/dist/clients-front /usr/share/nginx/html
#COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf
