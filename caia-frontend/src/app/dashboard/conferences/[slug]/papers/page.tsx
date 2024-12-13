import PapersConfListScreen from "@/core/papers/presentation/screens/papers-conf-list.screen";

interface Props {
  params: Promise<{
    slug: string;
  }>;
}

const PapersListPage = async (props: Props) => {
  const params = await props.params;

  const {
    slug
  } = params;

  return <PapersConfListScreen slug={slug} />;
};

export default PapersListPage;
