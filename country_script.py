import psycopg2

# اتصال بقاعدة البيانات المحلية
conn = psycopg2.connect(
    dbname="mydb",          # نفس الاسم اللي معرفه في docker-compose
    user="postgres",        # اليوزر بتاعك
    password="postgres",    # الباسورد
    host="localhost",       # هنا localhost عشان السكريبت شغال من جهازك
    port="5432"             # البورت المفتوح في docker-compose
)

cur = conn.cursor()

countries = [
    # ----------------- Middle East & North Africa -----------------
    {"name_en": "egypt", "name_ar": "مصر", "code": "EG", "currency": "EGP", "phone_code": "+20"},
    {"name_en": "saudi arabia", "name_ar": "السعودية", "code": "SA", "currency": "SAR", "phone_code": "+966"},
    {"name_en": "united arab emirates", "name_ar": "الإمارات", "code": "AE", "currency": "AED", "phone_code": "+971"},
    {"name_en": "kuwait", "name_ar": "الكويت", "code": "KW", "currency": "KWD", "phone_code": "+965"},
    {"name_en": "qatar", "name_ar": "قطر", "code": "QA", "currency": "QAR", "phone_code": "+974"},
    {"name_en": "bahrain", "name_ar": "البحرين", "code": "BH", "currency": "BHD", "phone_code": "+973"},
    {"name_en": "oman", "name_ar": "عمان", "code": "OM", "currency": "OMR", "phone_code": "+968"},
    {"name_en": "yemen", "name_ar": "اليمن", "code": "YE", "currency": "YER", "phone_code": "+967"},
    {"name_en": "iraq", "name_ar": "العراق", "code": "IQ", "currency": "IQD", "phone_code": "+964"},
    {"name_en": "jordan", "name_ar": "الأردن", "code": "JO", "currency": "JOD", "phone_code": "+962"},
    {"name_en": "syria", "name_ar": "سوريا", "code": "SY", "currency": "SYP", "phone_code": "+963"},
    {"name_en": "lebanon", "name_ar": "لبنان", "code": "LB", "currency": "LBP", "phone_code": "+961"},
    {"name_en": "palestine", "name_ar": "فلسطين", "code": "PS", "currency": "ILS", "phone_code": "+970"},
    {"name_en": "sudan", "name_ar": "السودان", "code": "SD", "currency": "SDG", "phone_code": "+249"},
    {"name_en": "morocco", "name_ar": "المغرب", "code": "MA", "currency": "MAD", "phone_code": "+212"},
    {"name_en": "algeria", "name_ar": "الجزائر", "code": "DZ", "currency": "DZD", "phone_code": "+213"},
    {"name_en": "tunisia", "name_ar": "تونس", "code": "TN", "currency": "TND", "phone_code": "+216"},
    {"name_en": "libya", "name_ar": "ليبيا", "code": "LY", "currency": "LYD", "phone_code": "+218"},
    {"name_en": "mauritania", "name_ar": "موريتانيا", "code": "MR", "currency": "MRU", "phone_code": "+222"},
    {"name_en": "somalia", "name_ar": "الصومال", "code": "SO", "currency": "SOS", "phone_code": "+252"},
    {"name_en": "comoros", "name_ar": "جزر القمر", "code": "KM", "currency": "KMF", "phone_code": "+269"},
    {"name_en": "djibouti", "name_ar": "جيبوتي", "code": "DJ", "currency": "DJF", "phone_code": "+253"},

    # ----------------- Europe -----------------
    {"name_en": "france", "name_ar": "فرنسا", "code": "FR", "currency": "EUR", "phone_code": "+33"},
    {"name_en": "germany", "name_ar": "ألمانيا", "code": "DE", "currency": "EUR", "phone_code": "+49"},
    {"name_en": "italy", "name_ar": "إيطاليا", "code": "IT", "currency": "EUR", "phone_code": "+39"},
    {"name_en": "spain", "name_ar": "إسبانيا", "code": "ES", "currency": "EUR", "phone_code": "+34"},
    {"name_en": "united kingdom", "name_ar": "المملكة المتحدة", "code": "GB", "currency": "GBP", "phone_code": "+44"},
    {"name_en": "russia", "name_ar": "روسيا", "code": "RU", "currency": "RUB", "phone_code": "+7"},
    {"name_en": "sweden", "name_ar": "السويد", "code": "SE", "currency": "SEK", "phone_code": "+46"},
    {"name_en": "norway", "name_ar": "النرويج", "code": "NO", "currency": "NOK", "phone_code": "+47"},
    {"name_en": "greece", "name_ar": "اليونان", "code": "GR", "currency": "EUR", "phone_code": "+30"},
    {"name_en": "turkey", "name_ar": "تركيا", "code": "TR", "currency": "TRY", "phone_code": "+90"},

    # ----------------- Asia -----------------
    {"name_en": "china", "name_ar": "الصين", "code": "CN", "currency": "CNY", "phone_code": "+86"},
    {"name_en": "india", "name_ar": "الهند", "code": "IN", "currency": "INR", "phone_code": "+91"},
    {"name_en": "japan", "name_ar": "اليابان", "code": "JP", "currency": "JPY", "phone_code": "+81"},
    {"name_en": "south korea", "name_ar": "كوريا الجنوبية", "code": "KR", "currency": "KRW", "phone_code": "+82"},
    {"name_en": "pakistan", "name_ar": "باكستان", "code": "PK", "currency": "PKR", "phone_code": "+92"},
    {"name_en": "indonesia", "name_ar": "إندونيسيا", "code": "ID", "currency": "IDR", "phone_code": "+62"},
    {"name_en": "malaysia", "name_ar": "ماليزيا", "code": "MY", "currency": "MYR", "phone_code": "+60"},
    {"name_en": "philippines", "name_ar": "الفلبين", "code": "PH", "currency": "PHP", "phone_code": "+63"},
    {"name_en": "thailand", "name_ar": "تايلاند", "code": "TH", "currency": "THB", "phone_code": "+66"},

    # ----------------- Americas -----------------
    {"name_en": "united states", "name_ar": "الولايات المتحدة", "code": "US", "currency": "USD", "phone_code": "+1"},
    {"name_en": "canada", "name_ar": "كندا", "code": "CA", "currency": "CAD", "phone_code": "+1"},
    {"name_en": "mexico", "name_ar": "المكسيك", "code": "MX", "currency": "MXN", "phone_code": "+52"},
    {"name_en": "brazil", "name_ar": "البرازيل", "code": "BR", "currency": "BRL", "phone_code": "+55"},
    {"name_en": "argentina", "name_ar": "الأرجنتين", "code": "AR", "currency": "ARS", "phone_code": "+54"},
    {"name_en": "chile", "name_ar": "تشيلي", "code": "CL", "currency": "CLP", "phone_code": "+56"},

    # ----------------- Africa -----------------
    {"name_en": "nigeria", "name_ar": "نيجيريا", "code": "NG", "currency": "NGN", "phone_code": "+234"},
    {"name_en": "ghana", "name_ar": "غانا", "code": "GH", "currency": "GHS", "phone_code": "+233"},
    {"name_en": "kenya", "name_ar": "كينيا", "code": "KE", "currency": "KES", "phone_code": "+254"},
    {"name_en": "ethiopia", "name_ar": "إثيوبيا", "code": "ET", "currency": "ETB", "phone_code": "+251"},
    {"name_en": "south africa", "name_ar": "جنوب أفريقيا", "code": "ZA", "currency": "ZAR", "phone_code": "+27"},

    # ----------------- Oceania -----------------
    {"name_en": "australia", "name_ar": "أستراليا", "code": "AU", "currency": "AUD", "phone_code": "+61"},
    {"name_en": "new zealand", "name_ar": "نيوزيلندا", "code": "NZ", "currency": "NZD", "phone_code": "+64"},
    {"name_en": "fiji", "name_ar": "فيجي", "code": "FJ", "currency": "FJD", "phone_code": "+679"},
]

for c in countries:
    cur.execute("""
        INSERT INTO countries (name_ar, name_en, code, currency, phone_code)
        VALUES (%s, %s, %s, %s, %s)
        ON CONFLICT (name_en) DO NOTHING
    """, (c["name_ar"], c["name_en"], c["code"], c["currency"], c["phone_code"]))
    print("name_ar" , c["name_ar"] , "is addess successfully")

conn.commit()
cur.close()
conn.close()
print("✅ Done, inserted countries")
