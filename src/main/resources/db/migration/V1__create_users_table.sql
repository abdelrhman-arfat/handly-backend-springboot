CREATE TYPE employee_role AS ENUM ('admin', 'editor', 'reader');
CREATE TYPE post_type AS ENUM ('video', 'image' , 'text');
CREATE TYPE user_role AS ENUM ('user', 'admin');
CREATE TYPE channel_team_status AS ENUM ('blocked', 'stopped', 'active', 'inactive');

CREATE TABLE countries (
                           id SERIAL PRIMARY KEY,
                           name_ar VARCHAR(100) UNIQUE NOT NULL,
                           name_en VARCHAR(100) UNIQUE NOT NULL,
                           code VARCHAR(20) UNIQUE NOT NULL,
                           currency VARCHAR(10) NOT NULL,
                           phone_code VARCHAR(10) NOT NULL,
                           is_deleted BOOLEAN DEFAULT FALSE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(225) NOT NULL,
                       phone VARCHAR(20) UNIQUE,
                       verified_at TIMESTAMP ,
                       country_id INT REFERENCES countries(id),
                       role user_role DEFAULT 'user',
                       is_deleted BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ;

CREATE INDEX idx_users_country_id ON users(country_id);
CREATE INDEX idx_users_role ON users(role);

CREATE TABLE platforms (
                           id SERIAL PRIMARY KEY,
                           name_ar VARCHAR(100) UNIQUE NOT NULL,
                           name_en VARCHAR(100) UNIQUE NOT NULL,
                           status boolean default false,
                           logo_url VARCHAR(255),
                           is_deleted BOOLEAN DEFAULT FALSE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE channels (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL UNIQUE,
                          owner_id INT REFERENCES users(id),
                          is_deleted BOOLEAN DEFAULT FALSE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_channels_owner_id ON channels(owner_id);

CREATE TABLE channel_platforms (
                                   id SERIAL PRIMARY KEY,
                                   channel_id INT REFERENCES channels(id),
                                   platform_id INT REFERENCES platforms(id),
                                   api_key TEXT NOT NULL,
                                   api_secret TEXT,
                                   is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE channel_team (
                              channel_id INT REFERENCES channels(id),
                              user_id INT REFERENCES users(id),
                              status channel_team_status NOT NULL DEFAULT 'active',
                              role employee_role NOT NULL DEFAULT 'reader',
                              expiration TIMESTAMP,
                              is_deleted BOOLEAN DEFAULT FALSE,
                              PRIMARY KEY(channel_id, user_id)
);

CREATE INDEX idx_channel_team_user_id ON channel_team(user_id);
CREATE INDEX idx_channel_team_role ON channel_team(role);

CREATE TABLE packages (
                          id SERIAL PRIMARY KEY,
                          name_ar VARCHAR(100) NOT NULL,
                          name_en VARCHAR(100) NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          photo_limits INT DEFAULT 0,
                          text_limits INT DEFAULT 0,
                          video_limits INT DEFAULT 0,
                          benefits TEXT,
                          duration_days INT NOT NULL,
                          is_deleted BOOLEAN DEFAULT FALSE,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE packages_subscription (
                                       id SERIAL PRIMARY KEY,
                                       channel_id INT REFERENCES channels(id),
                                       package_id INT REFERENCES packages(id),
                                       photo_used INT DEFAULT 0,
                                       video_used INT DEFAULT 0,
                                       text_used INT DEFAULT 0,
                                       start_date DATE DEFAULT CURRENT_DATE,
                                       end_date DATE,
                                       is_deleted BOOLEAN DEFAULT FALSE,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_packages_sub_channel_id ON packages_subscription(channel_id);
CREATE INDEX idx_packages_sub_package_id ON packages_subscription(package_id);

CREATE TABLE posts (
                       id SERIAL PRIMARY KEY,
                       channel_id INT REFERENCES channels(id),
                       poster_id INT REFERENCES users(id),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       start_date TIMESTAMP,
                       content text not null,
                       post_url TEXT,
                       type post_type NOT NULL,
                       is_deleted BOOLEAN DEFAULT FALSE
);

CREATE INDEX idx_posts_channel_id ON posts(channel_id);
CREATE INDEX idx_posts_poster_id ON posts(poster_id);
CREATE INDEX idx_posts_type ON posts(type);

CREATE TABLE post_platforms (
                                post_id INT REFERENCES posts(id),
                                channel_platform_id INT REFERENCES channel_platforms(id),
                                platform_post_id TEXT,
                                status VARCHAR(50) DEFAULT 'pending',
                                sent_at TIMESTAMP,
                                is_deleted BOOLEAN DEFAULT FALSE,
                                PRIMARY KEY(post_id, channel_platform_id)
);

CREATE INDEX idx_post_platforms_post_id ON post_platforms(post_id);
CREATE INDEX idx_post_platforms_channel_platform_id ON post_platforms(channel_platform_id);
