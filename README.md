### Elasticsearch Manager

다양한 Cloud Platform(AWS, GCP 등)에 손쉽게 Elasticsearch Cluster를 배포 및 Elasticsearch 관리를 위한 Open Source 제공

- Terraform : Cloud Resource Provisioning

- Helm : Deploy Elasticsearch Cluster in Kubernetes

- Spring Boot : Backend

#### 제공 기능

- **배포 할 Cloue Platform 선택** 
   - GCP

- **Elasticsearch의 Configuration을 사용자에 맞게 설정하여 Cloud에 배포**
  - 노드 Spec
  - Hot-Warm Node 수

- **Endpoint 제공**
  - Elasticsearch
  - Kibana (Elasticsearch Dashboard)
  - Cerebro (Elasticsearch Index Management)
- **배포 상태 확인**
    - Pod Metadata / Spec / Status
    
#### Elasticsearch Helm Chart
https://github.com/seongpyoHong/elasticsearch-helm-chart

---

#### TODO
- Multi User/Cluster 관리
- Grafana를 통한 Metric 제공
- Backup Storage 제공
- AWS 추가
