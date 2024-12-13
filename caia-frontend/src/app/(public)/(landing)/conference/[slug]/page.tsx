import ConferenceScreen from "@/core/conferences/presentation/screens/conference.screen";

interface Props {
  params: Promise<{
    slug: string;
  }>;
}

const PublicConferencePage = async (props: Props) => {
  const params = await props.params;

  const {
    slug
  } = params;

  return <ConferenceScreen slug={slug} />;
};

export default PublicConferencePage;
