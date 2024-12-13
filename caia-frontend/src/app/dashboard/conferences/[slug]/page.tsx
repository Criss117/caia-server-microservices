import ConferenceScreen from "@/core/conferences/presentation/screens/conference.screen";

interface Props {
  params: Promise<{
    slug: string;
  }>;
}

const ConferenceDashboardPage = async (props: Props) => {
  const params = await props.params;

  const {
    slug
  } = params;

  return <ConferenceScreen onDashboard slug={slug} />;
};

export default ConferenceDashboardPage;
