provider "google" {
  credentials = file("../credential/sphong-kuber.json")
  project = "sphong-kuber"
  region = "asia-northeast3"
}