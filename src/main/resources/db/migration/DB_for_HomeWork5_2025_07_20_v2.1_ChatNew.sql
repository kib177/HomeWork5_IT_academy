PGDMP  7    )                }            ChatNew    17.5    17.5     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    24610    ChatNew    DATABASE     �   CREATE DATABASE "ChatNew" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Belarusian_Belarus.1251';
    DROP DATABASE "ChatNew";
                     postgres    false            �            1259    32789    messages    TABLE     �   CREATE TABLE public.messages (
    id integer NOT NULL,
    sent_datetime timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    sender character varying(50),
    recipient character varying(50),
    text text NOT NULL
);
    DROP TABLE public.messages;
       public         heap r       postgres    false            �            1259    32788    messages_id_seq    SEQUENCE     �   CREATE SEQUENCE public.messages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.messages_id_seq;
       public               postgres    false    219            �           0    0    messages_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.messages_id_seq OWNED BY public.messages.id;
          public               postgres    false    218            �            1259    32782    users    TABLE     �  CREATE TABLE public.users (
    login character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    full_name character varying(100) NOT NULL,
    birth_date date NOT NULL,
    registration_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    role character varying(10) NOT NULL,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);
    DROP TABLE public.users;
       public         heap r       postgres    false            &           2604    32792    messages id    DEFAULT     j   ALTER TABLE ONLY public.messages ALTER COLUMN id SET DEFAULT nextval('public.messages_id_seq'::regclass);
 :   ALTER TABLE public.messages ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    218    219    219            �          0    32789    messages 
   TABLE DATA           N   COPY public.messages (id, sent_datetime, sender, recipient, text) FROM stdin;
    public               postgres    false    219   �       �          0    32782    users 
   TABLE DATA           `   COPY public.users (login, password, full_name, birth_date, registration_date, role) FROM stdin;
    public               postgres    false    217   �       �           0    0    messages_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.messages_id_seq', 25, true);
          public               postgres    false    218            ,           2606    32796    messages messages_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.messages DROP CONSTRAINT messages_pkey;
       public                 postgres    false    219            *           2606    32787    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (login);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    217            -           2606    32797     messages messages_from_user_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_from_user_fkey FOREIGN KEY (sender) REFERENCES public.users(login);
 J   ALTER TABLE ONLY public.messages DROP CONSTRAINT messages_from_user_fkey;
       public               postgres    false    217    4650    219            .           2606    32802    messages messages_to_user_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_to_user_fkey FOREIGN KEY (recipient) REFERENCES public.users(login);
 H   ALTER TABLE ONLY public.messages DROP CONSTRAINT messages_to_user_fkey;
       public               postgres    false    219    4650    217            �      x�m�=N�0�9��Q-�َ?��Q8�C�2�s��B(4!���p(��f����>���B�f%�
�>hJp���P�Ms߬����msW�kl��D3u�+��\�f�'��e|���s�>�*(˽Te�D��|��ȝ��h|����1ni�π2w&(ϥ���ib�~�G����D}Z`X�c^��s�'�g @��q�t�j�,Qm�C�zf�:�>��Cw�F#�)�-�ɜ�A^#��]���5Ɩ����CD��      �   �  x����r�P �5<�w�\�p�]D!E�tCQ#��`�U���1�.�I�g�}��tԌ]dw��|s�(�Ҕ�㠎`lu����P�1(�No�Z���l;ڄs�P��>��X׍��,�ma�a��+H� >EkHV�PA`�(��A�=��i-���f@��V��A�o����n��؊�D�\B���j�I���%r���~�=�Y;G��}}U�*��

@�2���վa�����J]�Q�퇕(�Z���gV6t1���x���L틃1*��ii�&�>�~:lc����/�=�)�k
�
�
��ܤ2%���Q�"L�䱟�U�.͆��i9��w����������:�;i�jp�,*�M���+D""�L/(�H��FB��ة�pB:M?lgZ��C��yUe��4ˈo-m���m�r9+ߦ�K
R�2O����?E�     