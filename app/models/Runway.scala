package models

case class Runway (id: String, airport_ref: String, airport_ident: String, length_ft: Int, width_ft: Int,
                  surface: String, lighted: Int, closed: Int, le_ident: String, le_latitude_deg: Float,
                  le_longitude_deg: Float, le_elevation_ft: Int, le_heading_degT: Float, le_displaced_threshold_ft: Int,
                  he_ident: String, he_latitude_deg: Float, he_longitude_deg: Float, he_elevation_ft: Int,
                  he_heading_degT: Int, he_displaced_threshold_ft: Int)