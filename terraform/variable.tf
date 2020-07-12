variable "project_id" {
  type = string
  default= "sphong-kuber"
}
variable "cluster_name" {
  type = string
  default = "cluster-1"
}
variable "node_spec" {
  type = string
  default = "n1-standard-1"
}
variable "node_region" {
  type = string
  default = "asia-northeast3"
}