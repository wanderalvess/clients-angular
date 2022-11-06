FROM node:lts-alpine as angular
ENV NODE_ENV=production
WORKDIR /app
COPY package.json /app
RUN npm install --silent
COPY . .
RUN npm run build --prod
FROM nginx:alpine
VOLUME /var/cache/nginx
COPY --from=angular app/dist/clients-front /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf


# docker build -t wanderalvess/clients-front:1.0.0 .
# docker run -p 8081:81 wanderalvess/clients-front:1.0.0
